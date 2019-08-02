package hoangviet.ndhv.ungdungtimhinh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
ImageView imageGoc,imageNhan;
public  static ArrayList<String> arrayName;
int REQUEST_CODE = 1;
String tenHinhGoc = "";
TextView txtdiemso ;
int diemso = 0 ;
SharedPreferences luuDiemSo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ánh xạ
        imageGoc = (ImageView)findViewById(R.id.imageGoc);
        imageNhan = (ImageView)findViewById(R.id.imageNhan);
        txtdiemso = (TextView)findViewById(R.id.textViewDiemSo);
        luuDiemSo = getSharedPreferences("dataDiem",MODE_PRIVATE);
        diemso= luuDiemSo.getInt("sodiem",0);

        txtdiemso.setText(diemso+"");
        //lấy ảnh từ tên ảnh trong chuỗi string
        String[] mangten = getResources().getStringArray(R.array.list_name);
        //truyen chuoi cho mang trong MainActivity
        arrayName =  new ArrayList<>(Arrays.asList(mangten));
        // trộn mảng
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(5);
        // thiet lap 1 id hinh
        int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
        imageGoc.setImageResource(idHinh);
        //hinh nhan
        imageNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,imageactivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            String tenHinhNhan = data.getStringExtra("tenHinhChon");
            int idHinhNhan = getResources().getIdentifier(tenHinhNhan,"drawable",getPackageName());
            imageNhan.setImageResource(idHinhNhan);
            if (tenHinhGoc.equals(tenHinhNhan)){
                Toast.makeText(this, "Chính xác rồi.. hehe", Toast.LENGTH_SHORT).show();
                diemso = diemso + 10;
                luuDiem();

                new CountDownTimer(2000,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrayName);
                        tenHinhGoc = arrayName.get(5);
                        // thiet lap 1 id hinh
                        int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
                        imageGoc.setImageResource(idHinh);
                    }
                }.start();
            }else{
                Toast.makeText(this, "Sai rồi bạn ơi! huhu", Toast.LENGTH_SHORT).show();
                diemso = diemso - 10 ;
                luuDiem();

            }
            txtdiemso.setText(diemso+"");

        }
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn hình...", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuhinhanh,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == R.id.menuhinhAnh){
           Collections.shuffle(arrayName);
           tenHinhGoc = arrayName.get(5);
           // thiet lap 1 id hinh
           int idHinh = getResources().getIdentifier(arrayName.get(5),"drawable",getPackageName());
           imageGoc.setImageResource(idHinh);
       }

        return super.onOptionsItemSelected(item);
    }
    private void luuDiem(){
        SharedPreferences.Editor editor = luuDiemSo.edit();
        editor.putInt("sodiem",diemso);
        editor.commit();
    }
}
