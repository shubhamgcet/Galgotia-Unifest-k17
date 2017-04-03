package com.gquasar.galgotiasunifest2017;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Fragment_Register extends Fragment {

    View mainView;
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public static final String URL_FORM = "https://docs.google.com/forms/d/e/1FAIpQLSd7TT0S2pMwvRghAxAwDrJ42sWvI7uhqQBdl1WIyKRXjyWRWQ/formResponse";

    public static final String NAME_KEY = "entry.2005620554";
    public static final String EMAIL_KEY = "entry.1045781291";
    public static final String NUMBER_KEY = "entry.1166974658";
    public static final String COLLEGE_KEY="entry.75064397";
    public static final String CHECK_KEY="entry.839337160";

    public String Categories[]={"Category 1","Category 2","Category 3","Category 4"},category;
    public String Events[]={"Event 1","Event 2","Event 3","Event 4"},event;
    public String Events2[]={"Event2 1","Event2 2","Event2 3","Event2 4"};

    private Context context;
    private EditText editName;
    private EditText editEmail;
    private EditText editPhoneNumber;
    private EditText editCollege;
    private TextView select;
    private CheckBox checkBox1,checkBox2;
    private Spinner spinner;
    private MultiSelectionSpinner spinner2;
    private RelativeLayout layout;
    ArrayAdapter<String> adapterCategoryCategory;
    ArrayAdapter<String> adapterEventCategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.fragment_fragment__register,container,false);
        mainView.setTag("FOUR");

        context = mainView.getContext();

        Button SubmitButton = (Button)mainView. findViewById(R.id.button_register);
        editName = (EditText)mainView. findViewById(R.id.editText_register_name);
        editEmail = (EditText)mainView. findViewById(R.id.editText_register_email);
        editPhoneNumber = (EditText)mainView. findViewById(R.id.editText_register_phone);
        editCollege = (EditText)mainView. findViewById(R.id.editText_register_college);
//        checkBox1=(CheckBox)mainView.findViewById(R.id.checkBox_event1);
//        checkBox2=(CheckBox)mainView.findViewById(R.id.checkBox_event2);
        spinner=(Spinner)mainView.findViewById(R.id.spinner);
        spinner2=(MultiSelectionSpinner)mainView.findViewById(R.id.multiSpinner);
        select=(TextView)mainView.findViewById(R.id.textViewSelect);
        //  layout=(RelativeLayout)mainView.findViewById(R.id.rl1);
        adapterCategoryCategory =new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, Categories);
        adapterCategoryCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoryCategory);
        spinner.setPrompt("Select Category");

        adapterEventCategory =new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, Events);
        adapterEventCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setItems(Events);
        spinner2.setPrompt("Select Event");
        spinner2.setSelection(0);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (validData()) {
//
//                    PostDataTask postDataTask = new PostDataTask();
//                    postDataTask.execute(URL_FORM, editName.getText().toString(),
//                            editEmail.getText().toString(),
//                            editPhoneNumber.getText().toString(), editCollege.getText().toString());//, options, option2);
//                }

                String st=spinner2.getSelectedItemsAsString();
                Toast.makeText(context, st, Toast.LENGTH_SHORT).show();
                Log.e("Selected",st);

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=adapterView.getItemAtPosition(i).toString();
                if(i==0)
                {
                    spinner2.setItems(Events);
                }
                else if (i==1)
                {
                    spinner2.setItems(Events2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                event=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return mainView;
    }

    class PostDataTask extends AsyncTask<String, Void, Boolean> {
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Please Wait..");
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String name = contactData[1];
            String email = contactData[2];
            String number = contactData[3];
            String college = contactData[4];

            String postBody = "";

            try {

                postBody = NAME_KEY + "=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + EMAIL_KEY + "=" + URLEncoder.encode(email, "UTF-8") +
                        "&" + NUMBER_KEY + "=" + URLEncoder.encode(number, "UTF-8")+
                        "&" + COLLEGE_KEY + "=" + URLEncoder.encode(college, "UTF-8");

            } catch (UnsupportedEncodingException ex) {
                result = false;
            }
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
            } catch (IOException exception) {
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progress.cancel();
            final AlertDialog.Builder alert=new AlertDialog.Builder(context);
            alert.setMessage(result ? "Successfully Registered!" : "There was some error in sending message. Please try again after some time.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editName.setText("");
                    editCollege.setText("");
                    editEmail.setText("");
                    editPhoneNumber.setText("");


                }
            });
            AlertDialog alertDialog=alert.create();
            alertDialog.show();
        }
    }

    boolean validData()
    {   String userName=editName.getText().toString();
        String userNumber = editPhoneNumber.getText().toString();
        String userEmail = editEmail.getText().toString();
        String userCollege=editCollege.getText().toString();

        if (userName.length()<3)
        {
            Toast.makeText(context, "Enter a Valid Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userNumber.length()!=10||userNumber.startsWith("0")||userNumber.startsWith("1")||userNumber.startsWith("2")||userNumber.startsWith("3")||userNumber.startsWith("4")||userNumber.startsWith("5")||userNumber.startsWith("6"))
        {
            Toast.makeText(context, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userEmail.length()<3)
        {
            Toast.makeText(context, "Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userCollege.length()<3)
        {
            Toast.makeText(context, "Enter a Valid College Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
