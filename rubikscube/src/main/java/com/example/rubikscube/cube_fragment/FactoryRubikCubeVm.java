package com.example.rubikscube.cube_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FactoryRubikCubeVm extends ViewModelProvider.NewInstanceFactory {
    RubikCube rubikCube;

    public FactoryRubikCubeVm(RubikCube rubikCube) {
        this.rubikCube = rubikCube;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RubikCubeViewModel.class) {
            return (T) new RubikCubeViewModel(rubikCube);
        }
        return null;
    }
}
