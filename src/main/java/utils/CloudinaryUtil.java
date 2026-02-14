package utils;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

        public static Cloudinary getCloudinary(String name, String apiKey, String apiSecret) {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", name);
            config.put("api_key", apiKey);
            config.put("api_secret", apiSecret);

            cloudinary = new Cloudinary(config);
        }

        return cloudinary;
    }
}
