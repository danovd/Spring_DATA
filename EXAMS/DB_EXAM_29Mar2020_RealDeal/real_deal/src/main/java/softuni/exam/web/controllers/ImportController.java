package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.PictureService;
import softuni.exam.service.SellerService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final CarService carService;
    private final OfferService offerService;
    private final PictureService pictureService;
    private final SellerService sellerService;

    @Autowired
    public ImportController(CarService carService, OfferService offerService, PictureService pictureService, SellerService sellerService) {
        this.carService = carService;
        this.offerService = offerService;
        this.pictureService = pictureService;
        this.sellerService = sellerService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.carService.areImported(),
                this.pictureService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.sellerService.areImported(),
                this.offerService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/sellers")
    public ModelAndView importSellers() throws IOException {
        String picturesXmlFileContent = this.sellerService.readSellersFromFile();
        return super.view("xml/import-sellers", "sellers", picturesXmlFileContent);
    }

    @PostMapping("/sellers")
    public ModelAndView importSellersConfirm() throws JAXBException, IOException {
        System.out.println(this.sellerService.importSellers());

        return super.redirect("/import/xml");
    }

    @GetMapping("/offers")
    public ModelAndView importOffers() throws IOException {
        String teamsXmlFileContent = this.offerService.readOffersFileContent();

        return super.view("xml/import-offers", "offers", teamsXmlFileContent);
    }

    @PostMapping("/offers")
    public ModelAndView importOffersConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.offerService.importOffers());

        return super.redirect("/import/xml");
    }

    @GetMapping("/cars")
    public ModelAndView importPlayers() throws IOException {
        String fileContent = this.carService.readCarsFileContent();

        return super.view("json/import-cars", "cars", fileContent);
    }

    @PostMapping("/cars")
    public ModelAndView importPlayersConfirm() throws IOException {
        System.out.println(this.carService.importCars());
        return super.redirect("/import/json");
    }

    @GetMapping("/pictures")
    public ModelAndView importPictures() throws IOException {
        String fileContent = this.pictureService.readPicturesFromFile();

        return super.view("json/import-pictures", "pictures", fileContent);
    }

    @PostMapping("/pictures")
    public ModelAndView importPicturesConfirm() throws IOException, JAXBException {
        System.out.println(this.pictureService.importPictures());
        return super.redirect("/import/json");
    }
}
