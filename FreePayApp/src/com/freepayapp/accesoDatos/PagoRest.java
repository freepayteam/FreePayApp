package com.freepayapp.accesoDatos;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.os.AsyncTask;

import com.freepayapp.modelo.Cobro;

public class PagoRest extends AsyncTask<Cobro, Void, String>{
	
	HttpClient httpClient = new DefaultHttpClient();
	private Exception exception;
	public AlertDialog.Builder msn;
	
	
	protected String doInBackground(Cobro... cobros) {
		try{
		HttpGet get = new HttpGet("http://181.143.12.230:8080/FreePay/webservice/Pago/"+cobros[0].codigoCobro()+"/"+cobros[0].codigoCuenta());
		get.setHeader("content-type", "text/xml");
		HttpResponse resp = httpClient.execute(get);
		String respuesta;
		
		if(resp.getStatusLine().getStatusCode()!=200){
			return respuesta="El Servicio de procesamiento de pago no está disponible";
		}
		
	    respuesta = EntityUtils.toString(resp.getEntity());
		return respuesta;
		} catch (Exception e) {
	            this.exception = e;
	            return null;
	    }
	
	}
	
    protected void onPostExecute(String respuesta) {
    	msn.setMessage(respuesta);
    	msn.show();
    }

}
