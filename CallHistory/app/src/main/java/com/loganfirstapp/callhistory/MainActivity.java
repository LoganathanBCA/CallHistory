package com.loganfirstapp.callhistory;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends Activity {

    TextView incomingText, outgoingText, missedText, allText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        incomingText = findViewById(R.id.incoming_calls);
        outgoingText = findViewById(R.id.outgoing_calls);
        missedText = findViewById(R.id.missed_calls);
        allText = findViewById(R.id.all_calls);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1")
					   .setIndicator("Incoming")
					   .setContent(R.id.incoming_tab));
        tabHost.addTab(tabHost.newTabSpec("tab2")
					   .setIndicator("Outgoing")
					   .setContent(R.id.outgoing_tab));
        tabHost.addTab(tabHost.newTabSpec("tab3")
					   .setIndicator("Missed")
					   .setContent(R.id.missed_tab));
        tabHost.addTab(tabHost.newTabSpec("tab4")
					   .setIndicator("All")
					   .setContent(R.id.all_tab));

        if (checkSelfPermission(Manifest.permission.READ_CALL_LOG)
			!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        } else {
            showCallLogs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int req, String[] perms, int[] results) {
        if (req == 1 && results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
            showCallLogs();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
        }
    }

	private void showCallLogs() {
		Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,
												   null, null, null, CallLog.Calls.DATE + " DESC");

		if (cursor == null) return;

		// Map<DateSection, Map<PhoneNumber, List<CallTimes>>>
		Map<String, Map<String, List<String>>> incomingMap = new LinkedHashMap<>();
		Map<String, Map<String, List<String>>> outgoingMap = new LinkedHashMap<>();
		Map<String, Map<String, List<String>>> missedMap = new LinkedHashMap<>();
		Map<String, Map<String, List<String>>> allMap = new LinkedHashMap<>();

		long now = System.currentTimeMillis();
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(now);
		int today = calToday.get(Calendar.DAY_OF_YEAR);
		int currentYear = calToday.get(Calendar.YEAR);

		while (cursor.moveToNext()) {
			String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
			long dateMillis = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
			int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(dateMillis);
			int callDay = cal.get(Calendar.DAY_OF_YEAR);
			int callYear = cal.get(Calendar.YEAR);

			String section;
			if (callYear == currentYear) {
				if (callDay == today) section = "Today";
				else if (callDay == (today - 1)) section = "Yesterday";
				else section = new SimpleDateFormat("d.M", Locale.getDefault()).format(new Date(dateMillis));
			} else {
				section = new SimpleDateFormat("d.M.yyyy", Locale.getDefault()).format(new Date(dateMillis));
			}

			String timeStr = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date(dateMillis));

			// Helper method to add call time to map
			addCallToMap(incomingMap, type, CallLog.Calls.INCOMING_TYPE, section, number, timeStr);
			addCallToMap(outgoingMap, type, CallLog.Calls.OUTGOING_TYPE, section, number, timeStr);
			addCallToMap(missedMap, type, CallLog.Calls.MISSED_TYPE, section, number, timeStr);

			// Add all calls regardless of type
			Map<String, List<String>> allGroup = allMap.getOrDefault(section, new LinkedHashMap<>());
			List<String> allTimes = allGroup.getOrDefault(number, new ArrayList<>());
			allTimes.add(timeStr);
			allGroup.put(number, allTimes);
			allMap.put(section, allGroup);
		}
		cursor.close();

		incomingText.setText(buildText(incomingMap));
		outgoingText.setText(buildText(outgoingMap));
		missedText.setText(buildText(missedMap));
		allText.setText(buildText(allMap));
	}

	private void addCallToMap(Map<String, Map<String, List<String>>> map, int actualType, int expectedType,
							  String section, String number, String timeStr) {
		if (actualType == expectedType) {
			Map<String, List<String>> group = map.getOrDefault(section, new LinkedHashMap<>());
			List<String> times = group.getOrDefault(number, new ArrayList<>());
			times.add(timeStr);
			group.put(number, times);
			map.put(section, group);
		}
	}

	private String buildText(Map<String, Map<String, List<String>>> map) {
		StringBuilder sb = new StringBuilder();

		for (String dateKey : map.keySet()) {
			// Detect if dateKey is "Today" or "Yesterday"
			if ("Today".equals(dateKey) || "Yesterday".equals(dateKey)) {
				sb.append("📅 ").append(dateKey).append("\n");
			} else {
				// Try parsing dateKey for day, month, and optional year
				try {
					Date date;
					if (dateKey.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
						// Format: d.M.yyyy
						date = new SimpleDateFormat("d.M.yyyy", Locale.getDefault()).parse(dateKey);
						String formatted = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date);
						sb.append("📅 ").append(formatted).append("\n");
					} else if (dateKey.matches("\\d{1,2}\\.\\d{1,2}")) {
						// Format: d.M (assumed current year)
						date = new SimpleDateFormat("d.M", Locale.getDefault()).parse(dateKey);
						// Append current year
						Calendar cal = Calendar.getInstance();
						int year = cal.get(Calendar.YEAR);
						String formatted = new SimpleDateFormat("dd MMM", Locale.getDefault()).format(date);
						sb.append("📅 ").append(formatted).append(" ").append(year).append("\n");
					} else {
						// Unknown format, just print as is
						sb.append("📅 ").append(dateKey).append("\n");
					}
				} catch (Exception e) {
					// Parsing failed - fallback to raw key
					sb.append("📅 ").append(dateKey).append("\n");
				}
			}

			Map<String, List<String>> calls = map.get(dateKey);
			for (String number : calls.keySet()) {
				List<String> times = calls.get(number);
				sb.append("📞 ").append(number)
					.append(" (").append(times.size()).append(")\n");
				for (String time : times) {
					sb.append("    🕒 ").append(time).append("\n");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}}
