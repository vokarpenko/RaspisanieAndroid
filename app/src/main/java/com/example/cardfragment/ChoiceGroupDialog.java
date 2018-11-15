package com.example.cardfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;
import static com.example.cardfragment.MainActivity.PREFS_FILE;
import static com.example.cardfragment.MainActivity.PREF_GROUP;


 class ChoiceGroupDialog  {
    private  Context context;
    private Spinner courseNumber,groupNumber,podgroupNumber;

    ChoiceGroupDialog(Context context){
       this.context =context;
    }

    void showHelp() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        android.support.v7.app.AlertDialog.Builder helpDialog;
        helpDialog = new android.support.v7.app.AlertDialog.Builder(context);
        View viewDialog = inflater.inflate( R.layout.help_dialog, null );
        helpDialog.setTitle("Выберите вашу группу")
                .setView(viewDialog)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        successDialog();
                    }
                })
                .create();
        createSpinners(viewDialog);
        helpDialog.show();
    }
    private void successDialog(){
        String myGroup = courseNumber.getSelectedItem().toString() + groupNumber.getSelectedItem().toString() + podgroupNumber.getSelectedItem().toString();
        SharedPreferences settings = context.getSharedPreferences(PREFS_FILE,MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_GROUP, myGroup);
        prefEditor.apply();
        Log.i("mytag",settings.getString(PREF_GROUP,""));
    }


    private  void createSpinners(View view){
        courseNumber = view.findViewById(R.id.course_number);
        String[] courseNumberString = {"1 курс","2 курс","3 курс","4 курс","1 курс маг.","2 курс маг."};
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,courseNumberString);
        courseNumber.setAdapter(courseAdapter);
        groupNumber = view.findViewById(R.id.group_number);
        podgroupNumber = view.findViewById(R.id.podgroup_number);
        courseNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        String[] groupNumberString = new String[10];
                        for (int i = 10; i <=19 ; i++) {
                            groupNumberString[i-10]=Integer.toString(i);
                        }
                        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                    case 1:
                        groupNumberString = new String[7];
                        groupNumberString[0]="21";
                        groupNumberString[1]="22";
                        groupNumberString[2]="23";
                        groupNumberString[3]="25";
                        groupNumberString[4]="26";
                        groupNumberString[5]="27";
                        groupNumberString[6]="28";
                        groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                    case 2:
                        groupNumberString = new String[5];
                        groupNumberString[0]="31";
                        groupNumberString[1]="32";
                        groupNumberString[2]="35";
                        groupNumberString[3]="36";
                        groupNumberString[4]="37";
                        groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                    case 3:
                        groupNumberString = new String[6];
                        groupNumberString[0]="КПМ";
                        groupNumberString[1]="КММ";
                        groupNumberString[2]="КИТ";
                        groupNumberString[3]="45";
                        groupNumberString[4]="46";
                        groupNumberString[5]="47";
                        groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                    case 4:
                        groupNumberString = new String[4];
                        groupNumberString[0]="202";
                        groupNumberString[1]="209";
                        groupNumberString[2]="212";
                        groupNumberString[3]="65";
                        groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                    case 5:
                        groupNumberString = new String[4];
                        groupNumberString[0]="302";
                        groupNumberString[1]="309";
                        groupNumberString[2]="312";
                        groupNumberString[3]="75";
                        groupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,groupNumberString);
                        groupNumber.setAdapter(groupAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        groupNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (courseNumber.getSelectedItem().toString()){
                    case "1 курс":
                        if (groupNumber.getSelectedItem().toString().equals("10")){
                            String[] podgroupNumberString = {""};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(false);
                        }
                        else {
                            String[] podgroupNumberString = {"1","2"};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        break;
                    case "2 курс":
                        if (groupNumber.getSelectedItem().toString().equals("23")
                                |groupNumber.getSelectedItem().toString().equals("28")){
                            String[] podgroupNumberString = {""};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(false);
                        }
                        else {
                            String[] podgroupNumberString = {"1","2"};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        break;
                    case "3 курс":
                        if (groupNumber.getSelectedItem().toString().equals("31")|groupNumber.getSelectedItem().toString().equals("32")){
                            String[] podgroupNumberString = {"КПМ","КММ","КИТ"};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        else{
                            String[] podgroupNumberString = {"1","2"};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        break;
                    case "4 курс":
                        if (groupNumber.getSelectedItem().toString().equals("КПМ")
                                |groupNumber.getSelectedItem().toString().equals("КММ")
                                |groupNumber.getSelectedItem().toString().equals("КММ")){
                            String[] podgroupNumberString = {""};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(false);
                        }
                        else{
                            String[] podgroupNumberString = {"1","2"};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        break;
                    case "1 курс маг.":
                        if (groupNumber.getSelectedItem().toString().equals("212")){
                            String[] podgroupNumberString = {"1","2"};

                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        else {
                            String[] podgroupNumberString = {""};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(false);
                        }
                        break;
                    case "2 курс маг.":
                        if (groupNumber.getSelectedItem().toString().equals("312")){
                            String[] podgroupNumberString = {"1","2"};

                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(true);
                        }
                        else {
                            String[] podgroupNumberString = {""};
                            ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,podgroupNumberString);
                            podgroupNumber.setAdapter(podgroupAdapter);
                            podgroupNumber.setEnabled(false);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*String[] podgroupNumberString = {"1","2"};
        ArrayAdapter<String> podgroupAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,podgroupNumberString);
        podgroupNumber.setAdapter(podgroupAdapter);
*/
    }
}
