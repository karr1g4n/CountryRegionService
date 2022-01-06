package tech.pragmat.countryregionservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.Dao.DaoImpl.DaoImpl;
import tech.pragmat.countryregionservice.model.entity.Country;

@RestController
@RequestMapping("/dao")
public class DaoController {

    private final DaoImpl dao = new DaoImpl();

    @PostMapping()
    public void add(@RequestBody Country Country) {
        dao.create(Country);
    }
}
