package com.pellcorp.android.vline.offline;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.pellcorp.android.vline.offline.VlineTimetableReceiver.Receiver;


public class VlineTimetableActivity extends Activity implements Receiver {
	private VlineTimetableReceiver receiver;
	private ProgressDialog progressDialog;
	private TableLayout resultsLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		resultsLayout = (TableLayout) findViewById(R.id.results_table);
		
		IntentFilter filter = new IntentFilter(VlineTimetableReceiver.ACTION_TIMETABLE);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		receiver = new VlineTimetableReceiver(this);
		registerReceiver(receiver, filter);
	}
	
	private void loadTimetable() {
		resultsLayout.removeAllViews();
			
		progressDialog = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.please_wait));
		Intent intent = new Intent(this, VlineTimetableService.class);
		startService(intent);
	}

	@Override
	public void onReceive(TimeTableResult results) {
		progressDialog.dismiss();
		
		if (results.getErrorMessage() == null) {
			populateSearchResults(results);
		} else {
			AlertDialog dialog = createErrorDialog(results.getErrorMessage());
			dialog.show();
		}
	}
	
	private void populateSearchResults(TimeTableResult result) {
		for(Date item : result.getToMelbourneTimetable()) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			
			TextView type = new TextView(this);
			type.setText(item.toString());
			tr.addView(type);
			
			resultsLayout.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
		}
	}
	
	private AlertDialog createErrorDialog(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
				.setCancelable(false)
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		return builder.create();
	}
}
