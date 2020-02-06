package com.dipesh.demoshopping.data.local;

import com.t7e.puraskar.data.local.db.dao.AreaMasterDao;
import com.t7e.puraskar.data.local.db.dao.EstablishmentAnswersDao;
import com.t7e.puraskar.data.local.db.dao.EstablishmentMasterDao;
import com.t7e.puraskar.data.local.db.dao.EstablishmentMechanicsDao;
import com.t7e.puraskar.data.local.db.dao.FeedbackMasterDao;
import com.t7e.puraskar.data.local.db.dao.MechanicRetailerTagDao;
import com.t7e.puraskar.data.local.db.dao.RewardsWishlistChaseDao;
import com.t7e.puraskar.model.tables.AmcoProducts;
import com.t7e.puraskar.model.tables.AreaMaster;
import com.t7e.puraskar.model.tables.BrandMaster;
import com.t7e.puraskar.model.tables.CatalogueRewardDetails;
import com.t7e.puraskar.model.tables.CheckInStatus;
import com.t7e.puraskar.model.tables.CircleProviderMaster;
import com.t7e.puraskar.model.tables.CityMaster;
import com.t7e.puraskar.model.tables.DeliveredRewardsList;
import com.t7e.puraskar.model.tables.EstablishmentAnswers;
import com.t7e.puraskar.model.tables.EstablishmentCategoryMaster;
import com.t7e.puraskar.model.tables.EstablishmentMaster;
import com.t7e.puraskar.model.tables.EstablishmentMechanics;
import com.t7e.puraskar.model.tables.EstablishmentTypes;
import com.t7e.puraskar.model.tables.FeedbackComments;
import com.t7e.puraskar.model.tables.FeedbackMaster;
import com.t7e.puraskar.model.tables.FeedbackPhotos;
import com.t7e.puraskar.model.tables.FeedbackTypes;
import com.t7e.puraskar.model.tables.LISAnswers;
import com.t7e.puraskar.model.tables.LISDynamicOptions;
import com.t7e.puraskar.model.tables.LISquestions;
import com.t7e.puraskar.model.tables.LogVisit;
import com.t7e.puraskar.model.tables.LucusProducts;
import com.t7e.puraskar.model.tables.MechanicAllPoints;
import com.t7e.puraskar.model.tables.MechanicRetailerTag;
import com.t7e.puraskar.model.tables.PointsMaster;
import com.t7e.puraskar.model.tables.ProductListWhole;
import com.t7e.puraskar.model.tables.ProductMaster;
import com.t7e.puraskar.model.tables.ProductsMichelin;
import com.t7e.puraskar.model.tables.ProgramMerchAndEstTagging;
import com.t7e.puraskar.model.tables.ProviderMaster;
import com.t7e.puraskar.model.tables.QuestionAnswerMaster;
import com.t7e.puraskar.model.tables.QuestionMaster;
import com.t7e.puraskar.model.tables.RedeemRewardsMaster;
import com.t7e.puraskar.model.tables.RetailerAnswer;
import com.t7e.puraskar.model.tables.RetailerListTable;
import com.t7e.puraskar.model.tables.RetailerOptionMaster;
import com.t7e.puraskar.model.tables.RetailerQuestionMaster;
import com.t7e.puraskar.model.tables.RewardsWishlistChase;
import com.t7e.puraskar.model.tables.SkfProducts;
import com.t7e.puraskar.model.tables.SkuMaster;
import com.t7e.puraskar.model.tables.TidcProducts;
import com.t7e.puraskar.model.tables.VehicleCategories;
import com.t7e.puraskar.model.tables.VisitFeedbackDetails;
import com.t7e.puraskar.model.tables.VisitFeedbackMechanics;
import com.t7e.puraskar.model.tables.VisitFeedbackMechanicsValue;
import com.t7e.puraskar.model.tables.VisitFeedbackType;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dipesh on 8/28/2017.
 */

public interface DbHelper {

    List<Long> insertEstablishmentMasters(List<EstablishmentMaster> establishmentMasters);
    Observable<List<EstablishmentMaster>> getAllUnsyncEstablishmentMasters();
    Observable<List<EstablishmentMaster>> getAllEstUnsyncForLogout();
    Observable<String> checkEstablishmentPhone(String phone);
}
