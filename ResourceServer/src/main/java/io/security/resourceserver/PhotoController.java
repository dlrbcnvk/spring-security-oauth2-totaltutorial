package io.security.resourceserver;

import io.security.sharedobject.Photo;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PhotoController {

    @GetMapping("/photos")
    public List<Photo> photos() {

        Photo photo1 = PhotoService.getBuild("1 ", "Album1 title ", "Album is nice ", "user1");
        Photo photo2 = PhotoService.getBuild("2 ", "Album2 title ", "Album is beautiful ", "user2");

        return Arrays.asList(photo1, photo2);
    }

    @GetMapping("/tokenExpire")
    public Map<String, Object> tokenExpire() {
        Map<String, Object> result = new HashMap<>();
        result.put("error", new OAuth2Error("invalid token", "token is expired", null));

        return result;
    }
}
