package access.accessapp.ui;

import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setClassName("access.accessapp", "access.accessapp.ui.MainActivity");
        startActivity(intent);
        finish();
    }
}
