package com.dipesh.demoshopping.data.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Dipesh on 8/31/2017.
 */

public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase){this.mAppDatabase = appDatabase;}

    @Override
    public List<Long> insertEstablishmentMasters(List<EstablishmentMaster> establishmentMasters) {
        return mAppDatabase.establishmentMasterDao().insertAll(establishmentMasters);
    }

    @Override
    public Observable<Long> updateLogVisitsToSync(List<String> logcodes) {
        return Observable.fromCallable(() -> mAppDatabase.logVisitDao().updateStatusToSynced(logcodes));
    }

}
