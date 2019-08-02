package hoangviet.ndhv.ungdungtimhinh;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class imageactivity extends Activity {
TableLayout myTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageactivity);
        myTable = (TableLayout)findViewById(R.id.tableLayout);
        int soDong = 4;
        int soCot = 3;
        // trộn mảng
        Collections.shuffle(MainActivity.arrayName);
        for (int i = 1 ; i<=soDong ; i++){
            TableRow tableRow =  new TableRow(this);
            for (int j = 1 ; j <= soCot ; j++){
                final int vitri = soCot * (i-1) + j-1;
                ImageView imageView =  new ImageView(this);
                //set kich thuoc cho hinh

                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
                TableRow.LayoutParams layoutParams =  new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);

                //them hinh vao tablerow
                tableRow.addView(imageView);
                //tao action cho hinh
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent();
                        intent.putExtra("tenHinhChon",MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            // them tablerow vao table
            myTable.addView(tableRow);
        }
    }
}
