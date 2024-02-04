import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caretakerapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SymptomsList extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText messageEditText;
    TextView welcomeTextView;
    ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recycler_view);
        messageEditText = findViewById(R.id.message_edit_text);
        welcomeTextView = findViewById(R.id.welcome_text);
        sendButton = findViewById(R.id.send_btn);

        sendButton.setOnClickListener((v) -> {
            String question = messageEditText.getText().toString().trim();
            Toast.makeText(this, question, Toast.LENGTH_LONG).show();
        });
    }
}