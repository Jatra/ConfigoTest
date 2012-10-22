package uk.co.jatra.android.test.configo;

import java.util.concurrent.Callable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import uk.co.jatra.android.configuration.Config;

public class Main extends Activity {

    private Config mConfig;
    
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        mConfig = Config.initConfig(this);
        
        TextView tv = (TextView)findViewById(R.id.tv1);
        String expiryString = mConfig.getStringPerExpiry("Not yet expired", "expired");
        tv.setText(expiryString+": "+mConfig.getSoftwareType()+" "+mConfig.getBuildType()+" expires: "+mConfig.getExpiryDate());
        try {
            String calledResult = mConfig.executePerExpiry(new Callable<String>() {
                public String call() {
                    return "Not expired";
                }
            }, new Callable<String>() {
                public String call() {
                    return "Expired";
                }
            });
            tv.setText(tv.getText()+"\n"+calledResult);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
