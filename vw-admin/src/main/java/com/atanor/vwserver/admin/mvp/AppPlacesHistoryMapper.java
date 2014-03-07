package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.places.NoPresetSelectedPlace;
import com.atanor.vwserver.admin.mvp.places.PresetSelectedPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ PresetSelectedPlace.Tokenizer.class, NoPresetSelectedPlace.Tokenizer.class })
public interface AppPlacesHistoryMapper extends PlaceHistoryMapper {
}
