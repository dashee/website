package com.confusedbrowser.androneee_remote.fragment;

import java.util.Observable;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.LinearLayout;
import com.confusedbrowser.androneee_remote.DrawHud;
import com.confusedbrowser.androneee_remote.PhonePosition;
import com.confusedbrowser.androneee_remote.R;
import android.widget.TextView;

/**
 * This is our HUD Fragment. which controls, sending and receiving controls
 * from our servers, and updating our HUD
 * 
 * @author David Buttar
 * @author Shahmir Javaid
 */
public class HudFragment extends Fragment
{
    /**
     * This is the variable where our HUD is drawn
     * We use DrawHud 
     */
    LinearLayout layout_hud;
    
    DrawHud draw_hud;
            
    /**
     * Handel to our TextViews
     */    
    private TextView textViewHudIpValue;
    private TextView textViewHudConnectionValue;
    private TextView textViewHudBpsValue; //Bytes Per Second
    private TextView textViewHudPitchValue; //Pitch Value
    private TextView textViewHudRollValue; //Pitch Value
	
    public HudFragment()
    {
    }
    
    /**
     * Set our HUD. Initiate the servers IP address so our thread can talk to it, 
     * start our thread and return the view which is required by this function
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_hud, container, false);

        layout_hud = (LinearLayout)view.findViewById(R.id.hud_canvas);
        draw_hud = new DrawHud (this.getActivity());
        layout_hud.addView(draw_hud);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

        // Set all of our textViews        
        textViewHudIpValue = (TextView)view.findViewById(R.id.hud_text_ip_value);
        textViewHudConnectionValue = (TextView)view.findViewById(R.id.hud_text_connection_value);
        textViewHudBpsValue = (TextView)view.findViewById(R.id.hud_text_bps_value);
        textViewHudPitchValue = (TextView)view.findViewById(R.id.hud_text_pitch_value);
        textViewHudRollValue = (TextView)view.findViewById(R.id.hud_text_roll_value);


        this.setHudIp(prefs.getString("pref_ip", "WTF"));
        this.setHudConnection("NoConnection");
        this.setHudBps(0);
        this.setHudPitch(0.0);
        this.setHudPitch(0.0);
        
        return view;
    }
    
    /**
     * Set our textbox ip value
     *
     * @param value - the ip address
     */
    public void setHudIp(String ip)
    {
        textViewHudIpValue.setText(ip);
    }
    
    /**
     * Set our textbox connection value
     *
     * @param value - the ip address
     */
    public void setHudConnection(String ip)
    {
        textViewHudConnectionValue.setText(ip);
    }
    
    /**
     * Set our textbox BytesPerSecond value
     *
     * @param bps - the bps value
     */
    public void setHudBps(int bps)
    {
        if (bps < 0)
            textViewHudBpsValue.setText("Negative?");

        textViewHudBpsValue.setText(Integer.toString(bps));
    }
    
    /**
     * Set our textbox Pitch value
     *
     * @param pitch - the pitch value
     */
    public void setHudPitch(double pitch)
    {
        if (pitch < 0.0)
            textViewHudPitchValue.setText("Negative?");
        
        DecimalFormat twoDecimal = new DecimalFormat("000.00");
        textViewHudPitchValue.setText(twoDecimal.format(pitch));
    }
    
    /**
     * Set our textbox Roll value
     *
     * @param roll - the pitch value
     */
    public void setHudRoll(double roll)
    {
        if (roll < 0.0)
            textViewHudRollValue.setText("Negative?");
    
        DecimalFormat twoDecimal = new DecimalFormat("000.00");
        textViewHudRollValue.setText(twoDecimal.format(roll));
    }

    /**
     * Pause our thread
     */
    public void onPause()
    {
        super.onPause();
    }
    
    /**
     * Resume our thread
     */
    public void onResume()
    {
        super.onResume();
    }

    public void rotateHud(float progress)
    {
        layout_hud.setRotation(-1.0f*(progress - 50.0f));
    }
}
