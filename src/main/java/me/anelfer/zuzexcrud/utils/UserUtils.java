package me.anelfer.zuzexcrud.utils;

import lombok.experimental.UtilityClass;
import me.anelfer.zuzexcrud.model.Citizen;
import me.anelfer.zuzexcrud.service.details.ZuzexUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class UserUtils {

    public Citizen getCurrentUser() {
        return ((ZuzexUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCitizen();
    }

}
