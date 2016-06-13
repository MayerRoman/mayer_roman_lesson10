package dao.impl;

import dao.DAOFactory;
import dao.SingerDAO;

/**
 * Created by Mayer Roman on 03.06.2016.
 */
public class XMLDAOFactory implements DAOFactory{

    @Override
    public SingerDAO getSingerDAO() {
        return new XMLSingerDAO();
    }
}
