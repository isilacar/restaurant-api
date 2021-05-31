package com.finartz.restaurantApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//bu sınıfımız tokenları oluşturmak,tokenları doğrulamak,signature kontrol etmek
//ve claims(payload da ki bilgiler) ilei zinleri kontrol etmekten sorumlu

@Component
public class TokenManager implements Serializable {
    private static final long serialVersionUID = -4237815441363201195L;

    //jwt oluşturulurken signature tarafı header+payload ve secretKey' i hashleyerek oluşturur
    private String secretKey = "finartz";


    //sisteme başarılı bir şekilde giriş olunduktan sonra bir token oluşturulmak için kullanılacak
    public String generateJwtToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        /*
        token oluşturmak için Jwts.builder ı kullanıyoruz,claims,payload bölümündeki bilgiler,
        subject kısmında myuserdetails sınıfımdan gelen username aldım,setIssuedAt,bu tokenın oluşturulma
        zamanı,setExpiration ise geçerlilik süresi,ben şimdiki zaman + 15 dakika olarak ayarladım
        ve signature mı yine hasliyorum burada,secretkeyimi de alıp.compact methoduyla da jwt mi oluşturmuş oluyorum
        */
        String authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        claims.put("authorities", authorities);

        Date date = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(date.getTime() + (15 * 60 * 1000));
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();

    }

    /*
    burada bir request attığımızda,alınan tokenı onaylamaya çalışıyoruz,Tokenı onaylamak demek,requestin authenticated
    olması ve o tokenın authenticated olan kişiye gönderilmiş olması demektir.
    Tokenı doğrulamak için önce onu ayrıştırmamız gerekiyor,yani parse işlemi yapıyoruz.
    Jwts sınıfımızda bu method tanımlı,bundan yararlanarak,parçalara ayırıyoruz.Claimsi almak için,
    parseClaimsJws methodunu kullanıyoruz ve getBody ile claim örneğimizi oluşturmuş oluyoruz.(payload bölümü)

    */
    public Boolean validateJwtToken(String token, UserDetails userDetails) {

        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
