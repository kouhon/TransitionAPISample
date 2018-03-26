package sample.transitionapisample

import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionRequest
import com.google.android.gms.location.DetectedActivity

class MainActivity : AppCompatActivity() {

    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.pendingIntent = PendingIntent.getActivity(
                this,
                0,
                Intent(this, TransitionIntentService::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT)
        this.activityRecognitionClient = ActivityRecognition.getClient(this)
        this.activityRecognitionClient
                .requestActivityTransitionUpdates(
                        ActivityTransitionRequest(
                            listOf(
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.IN_VEHICLE)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.IN_VEHICLE)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.ON_BICYCLE)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.ON_BICYCLE)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.ON_FOOT)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.ON_FOOT)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.RUNNING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.RUNNING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.STILL)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.STILL)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.TILTING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.TILTING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.UNKNOWN)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.UNKNOWN)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.WALKING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                                            .build(),
                                    ActivityTransition.Builder()
                                            .setActivityType(DetectedActivity.WALKING)
                                            .setActivityTransition(
                                                    ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                                            .build())),
                        this.pendingIntent)
                .addOnSuccessListener {
                    Toast.makeText(
                            this,
                            "requestActivityTransitionUpdates is Success.",
                            Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    it.printStackTrace()
                }
    }

    override fun onDestroy() {
        this.activityRecognitionClient
                .removeActivityTransitionUpdates(this.pendingIntent)
                .addOnSuccessListener {
                    this.pendingIntent.cancel()
                    Toast.makeText(
                            this,
                            "removeActivityTransitionUpdates is Success.",
                            Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    it.printStackTrace()
                }

        super.onDestroy()
    }
}
