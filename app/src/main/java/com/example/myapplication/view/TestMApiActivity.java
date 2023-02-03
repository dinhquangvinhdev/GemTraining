package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NewAdapter;
import com.example.myapplication.controller.ClientController;
import com.example.myapplication.databinding.ActivityTestMapiBinding;
import com.example.myapplication.mInterface.OnClickItemAdapter;
import com.example.myapplication.model.New;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestMApiActivity extends AppCompatActivity implements OnClickItemAdapter {
    private ActivityTestMapiBinding binding;
    private NewAdapter adapter;
    private int position;
    private List<New> arr;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestMapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        processData();
    }

    private void processData() {
        // getAllNews1();
        getAllNews2();
    }

    private void getAllNews2() {
        //Create a list in here
        arr = new ArrayList<>();

        ClientController.getInstance().getApi().getAllNew()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<New>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("bibi", "onSubcribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<New> news) {
                        Log.d("bibi", news.get(0).toString());
                        arr = news;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("bibi", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("bibi", "onComplete");
                        adapter = new NewAdapter(arr, TestMApiActivity.this);
                        binding.recyclerView.setAdapter(adapter);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.fabScrollUp.setVisibility(View.VISIBLE);
                        binding.fabAddNew.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void getAllNews1() {
        //get news through Api
        Call<List<New>> call = ClientController.getInstance().getApi().getData();
        //add request to enqueue
        call.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                if(response.isSuccessful()){
                    List<New> data = response.body();
                    adapter = new NewAdapter(data, TestMApiActivity.this);
                    binding.recyclerView.setAdapter(adapter);
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.fabScrollUp.setVisibility(View.VISIBLE);
                    binding.fabAddNew.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed for requesting api" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToFirstPage(View view) {
        if(binding.recyclerView != null)
            binding.recyclerView.getLayoutManager().scrollToPosition(0);
    }

    public void addNewModel(View view) {
        //addNew();
        addUser();
    }

    private void addUser() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_user);
        EditText edtNameUser = dialog.findViewById(R.id.edt_input_user_name);
        Button btnAdd = dialog.findViewById(R.id.btn_add_new);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameUser.getText().toString();
                Call<User> call = ClientController.getInstance().getApi().updateUser(name);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(getApplicationContext() , "" + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext() , "failed to post User into Api", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        dialog.show();
    }

    private void addNew() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_new);

        Button btnAdd = dialog.findViewById(R.id.btn_add_new);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        EditText edtIdUser, edtTitle, edtBody;
        edtIdUser = dialog.findViewById(R.id.edt_input_new_userid);
        edtTitle = dialog.findViewById(R.id.edt_input_new_title);
        edtBody = dialog.findViewById(R.id.edt_input_new_body);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new in here
                String idUser, title , body;
                idUser = edtIdUser.getText().toString();
                title = edtTitle.getText().toString();
                body = edtBody.getText().toString();
                if(!idUser.isEmpty() && !title.isEmpty() && !body.isEmpty()){
                    Call<New> call = ClientController.getInstance().getApi().createNew(
                            new New(title ,body, Integer.parseInt(idUser))
                    );
                    call.enqueue(new Callback<New>() {
                        @Override
                        public void onResponse(Call<New> call, Response<New> response) {
                            if(response.isSuccessful()){
                                String content = "";

                                content = "Code: " + response.code() + "\n"
                                        + "body: " + ((response.body() != null)? "" : response.body().toString());

                                Toast.makeText(getApplicationContext() , content, Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<New> call, Throwable t) {
                            Toast.makeText(getApplicationContext() , "failed to post New into Api", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel add new in here
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onClickItem(int position) {
        New tempNew = new New("sample title","sample body", 1);
        // putNew(position , tempNew);
        // patchNew(position, tempNew);
        deleteNew(position);
    }

    private void deleteNew(int position) {
        Call<Void> call = ClientController.getInstance().getApi().deleteNew(position + 1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext() , "Code: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "failed to post New into Api", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void patchNew(int position, New tempNew) {
        Call<New> call = ClientController.getInstance().getApi().patchNew(position + 1, tempNew);
        call.enqueue(new Callback<New>() {
            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                String content = "Code: " + response.code() + "\n"
                        + "body: " + response.body().toString();
                Toast.makeText(getApplicationContext() , content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<New> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "failed to post New into Api", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putNew(int position, New tempNew) {
        Call<New> call = ClientController.getInstance().getApi().putNew(position + 1, tempNew);
        call.enqueue(new Callback<New>() {
            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                String content = "Code: " + response.code() + "\n"
                            + "body: " + response.body().toString();
                Toast.makeText(getApplicationContext() , content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<New> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "failed to post New into Api", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null){
            disposable.dispose();
        }
    }
}