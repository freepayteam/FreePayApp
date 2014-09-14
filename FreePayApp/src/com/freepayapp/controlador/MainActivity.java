package com.freepayapp.controlador;

import com.freepayapp.accesoDatos.PagoRest;
import com.freepayapp.modelo.Cobro;

import in.blogspot.khurram2java.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText txtCodCobro;	
	AlertDialog.Builder msn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		txtCodCobro = (EditText) findViewById(R.id.txtCodCobro);		
		Button btnLeerCobro = (Button) findViewById(R.id.btnLeerCobro);
		Button btnPagar=(Button) findViewById(R.id.btnPagar);
		final AlertDialog.Builder msn = new AlertDialog.Builder(this);
		msn.setTitle("Alerta");  
		
		
	 //Leer codigo
     btnLeerCobro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();
				}
			}
		});
     
     //Pagar
     btnPagar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
				
					if(txtCodCobro.getText().toString().length()==0){
						msn.setMessage("Ingrese el codigo del cobro.");	
						msn.show(); 
						return;
					}
					
					int codigo=Integer.parseInt(txtCodCobro.getText().toString());
					if(codigo<=0){
						msn.setMessage("El codigo del cobro debe ser mayor a 0.");	
						msn.show(); 
						return;
					}
					
					Cobro cobro=new Cobro(codigo,2);
					PagoRest pago=new PagoRest();
					pago.msn=msn;
					pago.execute(cobro);		
					
					
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();
				}
			}
		});
     
     
	}
	
	//Finaliza la lectura del codigo QR
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				txtCodCobro.setText(intent.getStringExtra("SCAN_RESULT"));
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(getApplicationContext(), "Lectura de codigo cancelada.", 1).show();
			}
		}
	}

}
