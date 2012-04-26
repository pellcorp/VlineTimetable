package com.pellcorp.android.vline.offline;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import android.app.Application;

@ReportsCrashes(formKey = "dHVIazZTcEwyblRwQnYzT3I4RWswTkE6MQ",
	resToastText = R.string.crash_toast_text
)
public class VlineTimetableApplication extends Application {
	@Override
    public void onCreate() {
        ACRA.init(this);
        
        super.onCreate();
    }
}
