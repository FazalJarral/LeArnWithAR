package com.leARn.arcore.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.leARn.arcore.R;

import java.util.ArrayList;
import java.util.Locale;

public class BotFragment extends Fragment {
    private static final String TAG = HomeArFragment.class.getSimpleName();
    ModelRenderable modelRenderable;
    Scene scene;
    Node AstronautNode;
    SceneView sceneView;
    ArrayList<String> tips;
    TextView tip;
    Button button;
    int i, y;
    TextToSpeech mtts;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chatbot, container, false);
        sceneView = v.findViewById(R.id.sceneView);
        scene = sceneView.getScene();
        tip = v.findViewById(R.id.tip);
        button = v.findViewById(R.id.next);
        placeObject(Uri.parse("Astronaut.sfb"));
        tips = new ArrayList<>();
        tips.add("You Are Enough. Whatever you do, whatever happens, you are already 100% perfect");
        tips.add("Your Life is in Your Hand. You are 100% responsible for how your life turns out");
        tips.add("Never Settle for Less than You Can Be. Stand up for yourself!");
        tips.add("Create Balance in Your Life. ");
        tips.add("Feed Your Mind. ");
        tips.add("Free Your Mind.");

        i = 0;
        y = tips.size();
        tip.setText(tips.get(i));

      callTexttoSpeech();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == y) {
                    i = 0;
                }
                tip.setText(tips.get(i++));
                callTexttoSpeech();
                // tip.setText(tips.get(rand.nextInt()));
            }
        });
        return v;
    }

    private void callTexttoSpeech() {
        mtts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    mtts.setLanguage(Locale.ENGLISH);
                    mtts.speak(tip.getText().toString() , TextToSpeech.QUEUE_FLUSH , null , null);
                }
            }
        });
    }


    private void placeObject(Uri object) {
        ModelRenderable.builder()
                .setSource(getContext(), object)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(modelRenderable))
                .exceptionally(throwable -> {
                    Log.e("Error", throwable.getLocalizedMessage());
                    return null;
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        sceneView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            sceneView.resume();
        } catch (CameraNotAvailableException e) {
            e.printStackTrace();
        }

    }


    private void addNodeToScene(ModelRenderable renderable) {
//        AnchorNode anchorNode = new AnchorNode(createAnchor);
//        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
//        transformableNode.setName(object.toString());
//        transformableNode.setRenderable(renderable);
//        transformableNode.setParent(anchorNode);
//
//        fragment.getArSceneView().getScene().addChild(anchorNode);
//
//        transformableNode.setOnTapListener((hitTestResult, motionEvent) -> {
//            //Perform callback action, like bark
//        });
//        transformableNode.select();

        AstronautNode = new Node();
        AstronautNode.setParent(scene);
        AstronautNode.setLocalPosition(new Vector3(0f, 0f, -1));
        AstronautNode.setLocalScale(new Vector3(3f, 3f, 3f));
        AstronautNode.setRenderable(renderable);

        scene.addChild(AstronautNode);
    }

}
