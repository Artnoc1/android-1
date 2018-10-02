package mega.privacy.android.app.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import mega.privacy.android.app.CameraSyncService;
import mega.privacy.android.app.utils.Util;

import static mega.privacy.android.app.utils.JobUtil.startJob;


public class BootEventReceiver extends BroadcastReceiver {
	
	Handler handler = new Handler();
	
	public BootEventReceiver() {}

	@Override
	public void onReceive(final Context context, Intent intent){
//		Cursor cursor = context.getContentResolver().query(intent.getData(), null,null, null, null);
//	    cursor.moveToFirst();
//	    String image_path = cursor.getString(cursor.getColumnIndex("_data"));
//	    log("CameraEventReceiver_New Photo is Saved as : -" + image_path);
	    
		log("BootEventReceiver");
		final Context c = context;
	    
	    handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				log("Now I start the service");
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
					c.startService(new Intent(c, CameraSyncService.class));
                } else {
                    startJob(context);
                }
			}
		}, 5 * 1000);
	}
	
	public static void log(String message) {
		Util.log("BootEventReceiver", message);
	}
}
