package com.belykh.finalProj.service;

import com.belykh.finalProj.service.Impl.AddressServiceImpl;
import com.belykh.finalProj.service.Impl.FlowerServiceImpl;
import com.belykh.finalProj.service.Impl.LotServiceImpl;
import com.belykh.finalProj.service.Impl.UserServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Service objects.
 */
public class ServiceFactory {

    private final UserService userService = new UserServiceImpl();

    private final LotService lotService = new LotServiceImpl();

    private final FlowerService flowerService = new FlowerServiceImpl();

    private final AddressService addressService = new AddressServiceImpl();

    /**
     * Gets the address service.
     *
     * @return the address service
     */
    public AddressService getAddressService() {
        return addressService;
    }

    /**
     * Gets the flower service.
     *
     * @return the flower service
     */
    public FlowerService getFlowerService() {
        return flowerService;
    }

    /**
     * Gets the lot service.
     *
     * @return the lot service
     */
    public LotService getLotService() {
        return lotService;
    }


    /**
     * Gets the user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }
}
