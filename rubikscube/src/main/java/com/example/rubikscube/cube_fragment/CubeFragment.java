package com.example.rubikscube.cube_fragment;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.animatin_vm.AnimationViewModel;
import com.example.rubikscube.R;
import com.example.xube.test_opengl.RenderOpenGl;


public class CubeFragment extends Fragment {

    private AnimationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cube, container, false);
        GLSurfaceView glSurfaceView = view.findViewById(R.id.glSurfaceView);
        glSurfaceView.setEGLContextClientVersion(2);
        initVm();
        RenderOpenGl render = new RenderOpenGl(getContext(), viewModel, viewModel);
        glSurfaceView.setRenderer(render);

        return view;
    }
void initVm(){
    RubikCube rubikCube = FactoryCube.getInstance().createCube2x2(2);
    viewModel = ViewModelProviders.of(this,new FactoryRubikCubeVm(rubikCube)).get(RubikCubeViewModel.class);

}
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        viewModel.addMutable3dObject(rubikCube);
//        Camera camera = new Camera(new Vector3d(-2.0, -2.0, -3.0), new Vector3d(0.0, 0.0, 0.0), new Vector3d(0.0, 0.0, 0.0));
//        Vector3d vector3d = new Vector3d(-2.0, -2.0, -3.0);
//        camera.setEveVector3d(new Vector3d());
//        viewModel.setCamera(camera);

    }

    void initListener(View view){
//        view.findViewById(R.id.btnXUp).setOnClickListener((v)->{
//
//        });
//        view.findViewById(R.id.btnXDown).setOnClickListener((v)->{
//
//        });
//        view.findViewById(R.id.btnYUp).setOnClickListener((v)->{
//
//        });
//        view.findViewById(R.id.btnYDown).setOnClickListener((v)->{
//
//        });
//        view.findViewById(R.id.btnZUp).setOnClickListener((v)->{
//
//        });
//        view.findViewById(R.id.btnZDown).setOnClickListener((v)->{
//
//        });
//        viewModel.get
        view.findViewById(R.id.btnUp).setOnClickListener((v)->{

        });
        view.findViewById(R.id.btnDown).setOnClickListener((v)->{

        });
        view.findViewById(R.id.btnLeft).setOnClickListener((v)->{

        });
        view.findViewById(R.id.btnRight).setOnClickListener((v)->{

        });
    }

}
