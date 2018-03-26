package sample.transitionapisample

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.ActivityTransitionResult

/**
 * Created by kouichihonda on 2018/03/27.
 *
 */
class TransitionIntentService : IntentService(TransitionIntentService::class.java.name) {
    override fun onHandleIntent(intent: Intent?) {
        ActivityTransitionResult.extractResult(intent)?.transitionEvents?.forEach {
            Log.i(packageName, it.toString())
        }
    }
}