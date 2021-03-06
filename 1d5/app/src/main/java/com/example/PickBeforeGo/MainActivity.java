package com.example.PickBeforeGo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.PickBeforeGo.components.Product;
import com.example.PickBeforeGo.helper.GetProductHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    private static final String TAG = "Main activity";
    private ArrayList<Product> allProductArrayList;
    private ArrayList<Product> favouriteProductArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        // Get authentication
        FirebaseAuth.getInstance().getCurrentUser();

        //set up the bottom navigation bar
        BottomNavigationView btmNavBar = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,R.id.my_nav);
        NavigationUI.setupWithNavController(btmNavBar,navController);

        // Call database if product empty
        if (allProductArrayList == null){
            GetProductHelper getProduct = new GetProductHelper();
            allProductArrayList = getProduct.getAllProducts();
            favouriteProductArrayList = getProduct.getFavouriteProducts();
        }
    }

    //get methods for fragment to access activity products
    public ArrayList<Product> getAllProducts(){
        return this.allProductArrayList;
    }

    public ArrayList<Product> getFavouriteProducts(){
        return this.favouriteProductArrayList;
    }

}