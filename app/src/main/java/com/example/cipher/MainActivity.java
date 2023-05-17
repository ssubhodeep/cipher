package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.cipher.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        binding.buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encryptedText = binding.encryptedEditText.getText().toString();
                String decryptedText = viewModel.decryptText(encryptedText,getApplicationContext());
                binding.decryptedEdittext.setText(decryptedText);
            }
        });

        binding.buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plainText = binding.decryptedEdittext.getText().toString();
                String encryptedText = viewModel.encryptText(plainText,getApplicationContext());
                binding.encryptedEditText.setText(encryptedText);
            }
        });
    }
}