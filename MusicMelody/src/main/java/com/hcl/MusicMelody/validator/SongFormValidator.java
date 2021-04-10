package com.hcl.MusicMelody.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hcl.MusicMelody.dao.SongDAO;
import com.hcl.MusicMelody.form.SongForm;
import com.hcl.MusicMelody.models.Song;
 
@Component
public class SongFormValidator implements Validator {
 
   @Autowired
   private SongDAO songDAO;
 
   // This validator only checks for the ProductForm.
   @Override
   public boolean supports(Class<?> clazz) {
      return clazz == SongForm.class;
   }
 
   @Override
   public void validate(Object target, Errors errors) {
	   SongForm songForm = (SongForm) target;
 
      // Check the fields of ProductForm.
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
 
      String code = songForm.getCode();
      if (code != null && code.length() > 0) {
         if (code.matches("\\s+")) {
            errors.rejectValue("code", "Pattern.productForm.code");
         } else if (songForm.isNewSong()) {
        	 Song song = songDAO.findSong(code);
            if (song != null) {
               errors.rejectValue("code", "Duplicate.productForm.code");
            }
         }
      }
   }
 
}
