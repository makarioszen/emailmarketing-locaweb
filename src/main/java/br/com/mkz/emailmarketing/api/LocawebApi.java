package br.com.mkz.emailmarketing.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.mkz.emailmarketing.exceptions.RequestException;
import br.com.mkz.emailmarketing.exceptions.SendException;
import br.com.mkz.emailmarketing.model.Contato;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LocawebApi implements EmailMktApi {
	private String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36";
	private String hostname = "emailmkt15";
	private String login = "mundialinformatica1";
	private String idListas = "48253";// Cadastros recepção
	private String chave = "19cc6d9766bae69a8c9e8cb64101afea";
	private String status = "validos";

	@Override
	public int sendContato(Contato contato) throws SendException {
		// http://[HOSTNAME].locaweb.com.br/admin/api/[LOGIN]/contatos/importacao/?listas=[ID_LISTAS]&chave=[CHAVE]
		try {
			StringBuilder sbURL = new StringBuilder();
			sbURL.append("http://").append(hostname)
					.append(".locaweb.com.br/admin/api/").append(login)
					.append("/contatos/importacao/?listas=").append(idListas)
					.append("&chave=").append(chave);

			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			List<Contato> contatos = new ArrayList<>();
			contatos.add(contato);

			URL obj = new URL(sbURL.toString());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(gson.toJson(contatos));
			wr.flush();
			wr.close();

			return con.getResponseCode();

		} catch (Exception e) {
			throw new SendException(e.getMessage());
		}

	}

	@Override
	public List<Contato> getContatosFromList(long idList)
			throws RequestException {
		try {
			StringBuilder sbURL = new StringBuilder();
			sbURL.append("http://").append(hostname)
					.append(".locaweb.com.br/admin/api/").append(login)
					.append("/contatos/").append(status).append("/?chave=")
					.append(chave).append("&pagina=1");
			HttpURLConnection connection = null;
			URL url = new URL(sbURL.toString());
			connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			Type listType = new TypeToken<ArrayList<Contato>>() {
			}.getType();
			List<Contato> contatos = gson.fromJson(
					getStringFromInputStream(content), listType);
			return contatos;
		} catch (Exception e) {
			throw new RequestException(e.getMessage());
		}
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
