package com.example.login.Dao;

import com.example.login.Dto.VehiculosDto;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Objects;

public class DaoVehiculos {

    private DatabaseReference databaseReference;

    public DaoVehiculos(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference().child("Vehiculos");
    }

    public Task<Void> add(VehiculosDto car){

       return databaseReference.push().setValue(car);
    }

    public Task<Void> update(String key, HashMap<String, Object> haspmap){
       return databaseReference.child(key).updateChildren(haspmap);
    }

    public Task<Void> delete(String key){
        return databaseReference.child(key).removeValue();
    }


    public Query get(String key){
        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get(){
        return databaseReference.child("Vehiculos");
    }

}
