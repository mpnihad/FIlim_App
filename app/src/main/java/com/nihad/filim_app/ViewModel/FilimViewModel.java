package com.nihad.filim_app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.model.FilimModel;

import java.util.List;


public class FilimViewModel extends AndroidViewModel {
    private FilimModelRepository repository;
    private LiveData<List<FilimModel>> allNotes;

    public FilimViewModel(@NonNull Application application) {
        super(application);
        repository = new FilimModelRepository(application);
        allNotes = repository.getTasks();
    }

    public FilimModelRepository getRepository() {
        return repository;
    }

    public void insert(FilimModel filimModel) {
        repository.insertTask(filimModel);
    }

    public void update(FilimModel filimModel) {
        repository.updateTask(filimModel);
    }

    public void delete(FilimModel filimModel) {
        repository.deleteTask(filimModel);
    }
    public LiveData<Integer> getcount() {
        return repository.getcount();
    }



    public LiveData<List<FilimModel>> getAllFilim() {
        return repository.getTasks();
    }
}