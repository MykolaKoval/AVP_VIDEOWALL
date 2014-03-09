package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.places.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.places.PresetPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ PresetPlace.Tokenizer.class, DefaultPresetPlace.Tokenizer.class })
public interface AppPlacesHistoryMapper extends PlaceHistoryMapper {
}
