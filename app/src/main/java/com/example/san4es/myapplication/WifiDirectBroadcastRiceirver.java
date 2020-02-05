package com.example.san4es.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import java.io.IOException;

public class WifiDirectBroadcastRiceirver extends BroadcastReceiver {
    private WifiP2pManager nManager;
    private WifiP2pManager.Channel mChannel;
    private Wyfy mActivity;
    public WifiDirectBroadcastRiceirver(WifiP2pManager nnManager,WifiP2pManager.Channel mmChannel,Wyfy mmActivity){
        this.mActivity=mmActivity;
        this.mChannel=mmChannel;
        this.nManager=nnManager;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action))
        {
            int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
            if(state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                Toast.makeText(context,"Wifi is ON",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"Wifi is OFF",Toast.LENGTH_SHORT).show();
            }
        }
        else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){
            if(nManager!=null){
                nManager.requestPeers(mChannel,mActivity.peerListListener);
            }
        }
        else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){
            if(nManager==null){
                return;
            }
            NetworkInfo networkInfo= intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if(networkInfo.isConnected()){
                nManager.requestConnectionInfo(mChannel,mActivity.connectionInfoListener);
            }
            else{
              //  mActivity.ConnectonStatus.setText("Device disconrcted");
                if(Wyfy.serverClass!=null){
                try {
                    mActivity.serverClass.stop();
                    mActivity.serverClass.serverSocket.close();
                    mActivity.serverClass.socket.close();
                    mActivity.serverClass=null;
                    mActivity.serverClass.serverSocket=null;
                    mActivity.serverClass.socket=null;

                } catch (Exception e) {
                    e.printStackTrace();
                }}
                else{
                try {
                    mActivity.clientClass.stop();
                    mActivity.clientClass.socket.close();
                    mActivity.clientClass.socket=null;
                    mActivity.clientClass=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }}
            }
        }
        else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){}
    }
}
