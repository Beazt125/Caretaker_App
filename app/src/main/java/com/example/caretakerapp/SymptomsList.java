import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SymptomsList extends AppCompatActivity {

    private EditText userInput;
    private TextView chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userInput = findViewById(R.id.editTextUserInput);
        chatHistory = findViewById(R.id.textViewChatHistory);
    }

    public void sendMessage(View view) {
        String userMessage = userInput.getText().toString();
        new OpenAIRequestTask().execute(userMessage);
    }

    private class OpenAIRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return makeOpenAIRequest(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            updateChatHistory(result);
        }
    }

    private String makeOpenAIRequest(String userMessage) {
        try {
            URL url = new URL("https://api.openai.com/v1/engines/davinci/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer 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");

            JSONObject jsonInput = new JSONObject();
            jsonInput.put("prompt", userMessage);
            jsonInput.put("max_tokens", 150);

            OutputStream os = connection.getOutputStream();
            os.write(jsonInput.toString().getBytes());
            os.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private void updateChatHistory(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String generatedText = jsonResponse.getString("choices");
            chatHistory.append("ChatGPT: " + generatedText + "\n");
            userInput.getText().clear();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
