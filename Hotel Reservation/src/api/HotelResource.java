package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static service.CustomerService.*;
import static service.ReservationService.*;

public class HotelResource {

    // TODO 单例模式：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
    // TODO 总的来说：这是单例模式，一般用于比较大，复杂的对象，只初始化一次，应该还有一个 private的构造函数，使得不能用 new来实例化对象，
    //  只能调用getInstance方法来得到对象，而getInstance保证了每次调用都返回相同的对象。
    // TODO 对象的实例化方法，也是比较多的，最常用的方法是直接使用 new，而这是最普通的，如果要考虑到其它的需要，如单实例模式，层次间调用等等。
    // TODO *直接使用 new就不能实现好的设计，这时候需要使用间接使用 new，即getInstance方法。这是一个设计方式的代表，而不仅仅指代一个方法名。
    // TODO 1. new的使用:
    //  * 如Object _object = new Object()，这时候，就必须要知道有第二个Object的存在，而第二个Object也常常是在当前的应用程序域中的，可以被直接调用的。
    // TODO 2. GetInstance的使用:
    //  * 在主函数开始时调用，返回一个实例化对象，此对象是 static的，在内存中保留着它的引用，即内存中有一块区域专门用来存放静态方法和变量，
    //  * 可以直接使用，调用多次返回同一个对象。
    // TODO 3.两者区别对照:
    //  * 大部分类(非抽象类/接口/屏蔽了constructor的类)都可以用new, new就是通过生产一个新的实例对象，或者在栈上声明一个对象, 每部分的调用的都是一个新的对象。
    //  * getInstance是少部分类才有的一个方法，各自的实现也不同。
    //  * getInstance在单例模式(保证一个类仅有一个实例，并提供一个访问它的全局访问点)的类中常见，用来生成唯一的实例，getInstance往往是 static的。
    //  * (1) 对象使用之前通过getInstance得到而不需要自己定义，用完之后不需要delete；
    //  * (2) new 一定要生成一个新对象，分配内存；getInstance() 则不一定要再次创建，它可以把一个已存在的引用给你使用，这在效能上优于 new；
    //  * (3) new创建后只能当次使用，而getInstance()可以跨栈区域使用，或者远程跨区域使用。所以getInstance()通常是创建 static静态实例方法的。
    // TODO 4.总结：
    //  * getInstance这个方法在单例模式用的甚多，为了避免对内存造成浪费，直到需要实例化该类的时候才将其实例化，所以用getInstance来获取该对象，
    //  * 至于其他时候，也就是为了简便而已，为了不让程序在实例化对象的时候，不用每次都用 new关键字，索性提供一个instance方法，不必一执行这个类就
    //  * 初始化，这样做到不浪费系统资源！单例模式可以防止数据的冲突，节省内存空间。

    private static HotelResource hotelResource = null;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static Customer getCustomer(String email) {
        return findCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) throws Exception {
        addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom (String roomNumber) {
        return getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = findCustomer(customerEmail);
        return reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public static List<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = findCustomer(customerEmail);
        return getCustomerReservation(customer);
    }

    public static List<IRoom> findARoom (Date checkIn, Date checkOut) {
        return findRooms(checkIn, checkOut);
    }


}
