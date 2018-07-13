package viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.List;

import ar.edu.unq.mapunq_app_rest.MapunqApp;
import db.DataRepository;
import db.PointOfInterestEntity;

public class PointOfInterestViewModel extends AndroidViewModel {
    private final List<PointOfInterestEntity> mPois = new List<PointOfInterestEntity>;

    public PointOfInterestEntity poi = new PointOfInterestEntity();

    private String poiId;

    public PointOfInterestViewModel(@NonNull Application application, DataRepository repository,
                                    final String pointId) {
        super(application);
       poiId = pointId;

        poi = repository.loadPoi(poiId);
    }

    public List<PointOfInterestEntity> getPOIs() {
        return mPois;
    }

    public PointOfInterestEntity getPOI() {
        return poi;
    }

    public void setPOI(PointOfInterestEntity newpoi) {
        poi = new PointOfInterestEntity(newpoi);

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private String mPoiId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, String mPoiId) {
            mApplication = application;
            mPoiId = mPoiId;
            mRepository = ((MapunqApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PointOfInterestViewModel(mApplication, mRepository, mPoiId);
        }
    }
}


