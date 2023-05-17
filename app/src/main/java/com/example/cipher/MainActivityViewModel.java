package com.example.cipher;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    public String decryptText(String encryptedText, Context context) {
        // Perform decryption logic here
        String decryptedText = EncryptionUtil.decrypt(encryptedText, context);
        return decryptedText;
    }

    public String encryptText(String plainText, Context context) {
        String encryptedText = EncryptionUtil.encrypt(plainText, context);
        return encryptedText;
    }

}
