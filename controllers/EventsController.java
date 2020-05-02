package controllers;

/* Controller class for the events that takes information from the model
 * and passes it to the view
 *
 * @author Kahlie
 * @date 4/29/20
 */
import api.adapters.DatabaseAdapter;
import java.io.IOException;
import java.util.Map;
import models.EventsModel;
import org.json.JSONException;

public class EventsController {

    public UUIDController uuidController = new UUIDController();

    private DatabaseAdapter da = new DatabaseAdapter();

    private int zipcode;

    //All category keys for the events in the categories list
    private final String comedy = "comedy";
    private final String music = "music";
    private final String conference = "conference";
    private final String education = "education";
    private final String family = "family";
    private final String festivals = "festivals_parades";
    private final String film = "movies";
    private final String foodandWine = "wine";
    private final String charity = "fundraisers";
    private final String exhibits = "art";
    private final String health = "support";
    private final String holiday = "holiday";
    private final String books = "books";
    private final String attractions = "Museums";
    private final String neighborhood = "community";
    private final String business = "business";
    private final String nightlife = "singles_social";
    private final String university = "schools_alumni";
    private final String meetups = "organizations";
    private final String recreation = "outdoors_recreation";
    private final String performingArts = "performing_arts";
    private final String pets = "animals";
    private final String politics = "activism";
    private final String retail = "sales";
    private final String science = "science";
    private final String religion = "religion";
    private final String sports = "sports";
    private final String technology = "technology";
    private final String others = "other";

    protected EventsModel eventsModel = new EventsModel();

    //=================  GETTERS ===============
    public Map getCategoriesMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.loadCategories();
        return this.eventsModel.getCategoriesMap();
    }

    //=================  Comedy ===============
    public Map getComedyEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.comedy, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getComedyEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getComedyEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getComedyEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getComedyEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getComedyEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Music ===============
    public Map getMusicEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.music, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getMusicEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getMusicEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getMusicEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getMusicEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getMusicEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Conference ===============
    public Map getConferenceEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.conference, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getConferenceEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getConferenceEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getConferenceEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getConferenceEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getConferenceEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= education ===============
    public Map getEducationEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.education, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getEducationEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getEducationEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getEducationEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getEducationEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getEducationEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= family ===============
    public Map getFamilyEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.family, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getFamilyEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getFamilyEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getFamilyEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getFamilyEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getFamilyEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= festivals ===============
    public Map getFestivalsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.festivals, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getFestivalsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getFestivalsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getFestivalsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getFestivalsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getFestivalsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= film ===============
    public Map getFilmEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.film, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getFilmEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getFilmEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getFilmEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getFilmEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getFilmEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= food&wine ===============
    public Map getFoodandWineEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.foodandWine, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getFoodandWineEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getFoodandWineEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getFoodandWineEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getFoodandWineEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getFoodandWineEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= fundraisers ===============
    public Map getCharityEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.charity, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getCharityEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getCharityEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getCharityEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getCharityEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getCharityEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= exhibits ===============
    public Map getExhibitsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.exhibits, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getExhibitsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getExhibitsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getExhibitsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getExhibitsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getExhibitsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= health ===============
    public Map getHealthEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.health, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getHealthEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getHealthEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getHealthEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getHealthEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getHealthEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= holiday ===============
    public Map getHolidayEventTitleMap() throws IOException, JSONException {
        this.eventsModel = this.eventsModel.setMapsBasedOnEvent(this.holiday, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getHolidayEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getHolidayEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getHolidayEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getHolidayEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getHolidayEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= books ===============
    public Map getBooksEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.books, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getBooksEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getBooksEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getBooksEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getBooksEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getBooksEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= attractions ===============
    public Map getAttractionsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.attractions, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getAttractionsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getAttractionsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getAttractionsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getAttractionsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getAttractionsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Neighborhood ===============
    public Map getNeighborhoodEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.neighborhood, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getNeighborhoodEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getNeighborhoodEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getNeighborhoodEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getNeighborhoodEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getNeighborhoodEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Business ===============
    public Map getBusinessEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.business, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getBusinessEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getBusinessEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getBusinessEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getBusinessEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getBusinessEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Nightlife ===============
    public Map getNightlifeEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.nightlife, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getNightlifeEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getNightlifeEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getNightlifeEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getNightlifeEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getNightlifeEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= University ===============
    public Map getUniversityEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.university, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getUniversityEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getUniversityEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getUniversityEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getUniversityEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getUniversityEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Meetup ===============
    public Map getMeetupEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.meetups, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getMeetupEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getMeetupEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getMeetupEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getMeetupEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getMeetupEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Recreation ===============
    public Map getRecreationEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.recreation, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getRecreationEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getRecreationEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getRecreationEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getRecreationEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getRecreationEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= PerformingArts ===============
    public Map getPerformingArtsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.performingArts, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getPerformingArtsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getPerformingArtsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getPerformingArtsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getPerformingArtsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getPerformingArtsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Pets ===============
    public Map getPetsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.pets, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getPetsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getPetsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getPetsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getPetsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getPetsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Politics ===============
    public Map getPoliticsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.politics, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getPoliticsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getPoliticsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getPoliticsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getPoliticsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getPoliticsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Retail ===============
    public Map getRetailEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.retail, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getRetailEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getRetailEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getRetailEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getRetailEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getRetailEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Science ===============
    public Map getScienceEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.science, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getScienceEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getScienceEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getScienceEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getScienceEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getScienceEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Religion ===============
    public Map getReligionEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.religion, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getReligionEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getReligionEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getReligionEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getReligionEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getReligionEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Sports ===============
    public Map getSportsEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.sports, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getSportsEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getSportsEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getSportsEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getSportsEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getSportsEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Technology ===============
    public Map getTechnologyEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.technology, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getTechnologyEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getTechnologyEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getTechnologyEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getTechnologyEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getTechnologyEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }

    //================= Others ===============
    public Map getOthersEventTitleMap() throws IOException, JSONException {
        this.eventsModel = eventsModel.setMapsBasedOnEvent(this.others, this.zipcode);
        return this.eventsModel.getEventTitleMap();
    }

    public Map getOthersEventDescriptionMap() throws IOException, JSONException {
        return this.eventsModel.getEventDescriptionMap();
    }

    public Map getOthersEventVenueNameMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueNameMap();
    }

    public Map getOthersEventImageMap() throws IOException, JSONException {
        return this.eventsModel.getEventImageMap();
    }

    public Map getOthersEventUrlMap() throws IOException, JSONException {
        return this.eventsModel.getEventUrlMap();
    }

    public Map getOthersEventVenueAddressMap() throws IOException, JSONException {
        return this.eventsModel.getEventVenueAddressMap();
    }
    
    
    //================= SETTERS ===============
    public void setZipCode() {
        this.zipcode = Integer.parseInt(this.da.queryForAttribute(this.uuidController.getUUID(), "zipcode"));
        System.out.println("searching for events with zipcode: " + this.zipcode);
    }
}
