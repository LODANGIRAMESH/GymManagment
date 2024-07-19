package com.decoders.gymManagementSystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.decoders.gymManagementSystem.bean.GymBook;
import com.decoders.gymManagementSystem.bean.GymItem;
import com.decoders.gymManagementSystem.bean.Item;
import com.decoders.gymManagementSystem.bean.Slot;
import com.decoders.gymManagementSystem.bean.SlotItem;
import com.decoders.gymManagementSystem.bean.SlotItemEmbed;
import com.decoders.gymManagementSystem.dao.GymBookDao;
import com.decoders.gymManagementSystem.dao.GymBookImpl;
import com.decoders.gymManagementSystem.dao.GymItemDao;
import com.decoders.gymManagementSystem.dao.SlotDao;
import com.decoders.gymManagementSystem.dao.SlotDaoImpl;
import com.decoders.gymManagementSystem.dao.SlotItemDao;
import com.decoders.gymManagementSystem.dao.SlotItemDaoImpl;
import com.decoders.gymManagementSystem.exception.AlreadyBookedException;
import com.decoders.gymManagementSystem.exception.SeatNotAvailableException;
import com.decoders.gymManagementSystem.service.GymItemService;
import com.decoders.gymManagementSystem.service.GymUserService;

@RestController
public class GymController {
	
	@Autowired
	private GymItemDao gymItemDao;
	
	@Autowired
	private SlotDaoImpl slotDaoImpl;
	
	@Autowired
	private GymUserService gymUserService;
	
	@Autowired
	private SlotItemDaoImpl slotItemDaoImpl;
	
	@Autowired 
	private GymItemService itemService;
	
	@Autowired
	private GymUserService userService;
	
	@Autowired
	private SlotDao slotDao;
	
	@Autowired
	private SlotItemDao slotItemDao;
	@Autowired
	private GymBookImpl  gymBookImpl;
	
	@Autowired 
	private GymBookDao gymBookDao;
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(GymController.class);
	
	@GetMapping("/")
	public ModelAndView indexPageRouting1() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/index")
	public ModelAndView indexPageRouting2() {
		String indexPage="";
		String userType=userService.getType();
		if(userType.equalsIgnoreCase("Admin"))
			indexPage="controlPanel";
		else if(userType.equalsIgnoreCase("MEMBER"))
			indexPage="controlPanel2";
		return new ModelAndView(indexPage);
	}
	
	
	@GetMapping("/gymitem")
	public ModelAndView showItemEntryPage() {
		GymItem gymItem = new GymItem();
		ModelAndView mv = new ModelAndView("gymItemEntryPage");
		mv.addObject("itemRecord", gymItem);
		return mv;
	}
	
	@PostMapping("/gymitem")
	public ModelAndView saveItem(@ModelAttribute("itemRecord") GymItem gymItem) {
		gymItemDao.saveNewItem(gymItem);
		return new ModelAndView("gymItemReportPage");
	}
	
	@GetMapping("/gymitems")
	public ModelAndView showItemReportPage() {
		List<GymItem> itemList = gymItemDao.displayAllItem();
		ModelAndView mv = new ModelAndView("gymItemReportPage");
		mv.addObject("itemList", itemList);
		
		return mv;
	}
	
	@GetMapping("/add-slot")
	public ModelAndView showSlotEntryPage() {
		Slot slot = new Slot();
		ModelAndView mv = new ModelAndView("slotEntryPage");
		mv.addObject("slotRecord",slot);
		return mv;
	}
	
	@PostMapping("/slot")
	public ModelAndView saveSlot(@ModelAttribute("slotRecord") Slot slot) {
		slotDaoImpl.saveNewSLot(slot);
		
		List<GymItem> itemList = gymItemDao.displayAllItem();
		
		for(GymItem item : itemList) {
			SlotItemEmbed embeddedId = new SlotItemEmbed(slot.getSlotId(), item.getItemId());
			SlotItem slotItem = new SlotItem(embeddedId);
			slotItemDaoImpl.saveSlotItem(slotItem);
		}
		
		return new ModelAndView("controlPanel");
	}
	
	@GetMapping("/slots")
	public ModelAndView showSlotReport() {
		List<Slot> slotList = slotDaoImpl.displayAllSlot();
		ModelAndView mv = new ModelAndView("slotReport");
		mv.addObject("slotList", slotList);
		
		return mv;
	}
	@GetMapping("/gymBookList")
	public ModelAndView showSlotBooked() {
	    List<GymBook> bookList = gymBookDao.getBookList();
	    if (gymUserService.getType().equalsIgnoreCase("MEMBER")) {
	        String username = gymUserService.getUser().getUsername();
	        bookList = bookList.stream()
	                           .filter(gymBook -> gymBook.getUsername().equals(username))
	                           .toList();  // Corrected to use Collectors.toList()
	    }
	    ModelAndView mv = new ModelAndView("slotBookedReport");
	    mv.addObject("bookList", bookList);
	    return mv;
	}
	@GetMapping("/slot-show/{id}")
	public ModelAndView showSlotBookPage(@PathVariable Long id) {
		String fname="";
		String userType=userService.getType();
		if(userType.equalsIgnoreCase("Admin"))
			fname="slotBookingAdminInterface";
		else if(userType.equalsIgnoreCase("MEMBER"))
			fname="slotBookingUserInterface";
		Slot slot=slotDao.findSlotById(id);
		List<Item> itemList=itemService.getItemsList(id);
		ModelAndView mv=new ModelAndView(fname);
		mv.addObject("slot",slot);
		mv.addObject("itemList",itemList);
		if(userType.equalsIgnoreCase("Admin")) {
			List<String> userList=userService.getAllCustomers();
			mv.addObject("userList",userList);
		}
		return mv;
	}
	/*@PostMapping("/slot-book")
    public ModelAndView saveSlotBookPage(@RequestParam("slotId") Long slotId,@RequestParam("itemId") Long itemId,@RequestParam("userId") String userId) {
    	GymItem gymItem=gymItemDao.findItemById(itemId);
    	SlotItemEmbed embed=new SlotItemEmbed(slotId,itemId);
    	int totalSeat=gymItem.getTotalSeat();
    	SlotItem slotItem=slotItemDao.findItemById(embed);
    	int seatBooked=slotItemDao.findSeatBookedById(embed);
    	int available=totalSeat-seatBooked;
    	GymBook gymBook=null;
    	if(available>0) {
    		Long bookingId=gymBookDao.generateBookingId();
    		seatBooked++;
    		slotItem.setSeatBooked(seatBooked);
    		String customerId="";
    		if(userId.equals("0"))
    			customerId=userService.getUser().getUsername();
    		else
    			customerId=userId;
    		gymBook=new GymBook(bookingId,slotId,itemId,customerId);
    		slotItemDao.saveSlotItem(slotItem);
    		gymBookDao.save(gymBook);
    	}
    	else {
    		throw new SeatNotAvailableException();
    		
    	}
    	return new ModelAndView("redirect:/index");
    }*/
	@PostMapping("/slot-book")
	public ModelAndView saveSlotBookPage(@RequestParam("slotId") Long slotId, @RequestParam("itemId") Long itemId, @RequestParam("userId") String userId) {
	    GymItem gymItem = gymItemDao.findItemById(itemId);
	    SlotItemEmbed embed = new SlotItemEmbed(slotId, itemId);
	    int totalSeat = gymItem.getTotalSeat();
	    SlotItem slotItem = slotItemDao.findItemById(embed);
	    int seatBooked = slotItemDao.findSeatBookedById(embed);
	    int available = totalSeat - seatBooked;
	    GymBook gymBook = null;
	    
	    String customerId = "";
	    if (userId.equals("0")) {
	        customerId = userService.getUser().getUsername();
	    } else {
	        customerId = userId;
	    }
	    
	    // Check if the user has already booked this slot
	    List<GymBook> userBookings = gymBookDao.getEntitiesByUsername(customerId);
	    for (GymBook booking : userBookings) {
	        if (booking.getSlotId().equals(slotId)) {
	            throw new AlreadyBookedException();
	        }
	    }
	    
	    if (available > 0) {
	        Long bookingId = gymBookDao.generateBookingId();
	        seatBooked++;
	        slotItem.setSeatBooked(seatBooked);
	        gymBook = new GymBook(bookingId, slotId, itemId, customerId);
	        slotItemDao.saveSlotItem(slotItem);
	        gymBookDao.save(gymBook);
	    } else {
	        throw new SeatNotAvailableException();
	    }
	    
	    return new ModelAndView("redirect:/index");
	}

	/*@GetMapping("/delete-booking/{bookingId}")
	public ModelAndView deleteBooking(@PathVariable Long bookingId) {
	    System.out.println("Received bookingId: " + bookingId); // Debug logging
	    GymBook booking = gymBookDao.findBookInfoById(bookingId);
	    String username = userService.getUser().getUsername();
	    if (booking != null && booking.getUsername().equals(username)) {
	        SlotItemEmbed embed = new SlotItemEmbed(booking.getSlotId(), booking.getItemId());
	        SlotItem slotItem = slotItemDao.findItemById(embed);
	        
	        if (slotItem != null && slotItem.getSeatBooked() > 0) {
	            slotItem.setSeatBooked(slotItem.getSeatBooked() - 1);
	            slotItemDao.saveSlotItem(slotItem);
	        }
	        gymBookDao.deleteById(bookingId);
	    }
	    return new ModelAndView("redirect:/index");
	}*/
	@GetMapping("/delete-booking/{bookingId}")
	public ModelAndView deleteBooking(@PathVariable Long bookingId) {
	    System.out.println("Received bookingId: " + bookingId); // Debug logging
	    GymBook booking = gymBookDao.findBookInfoById(bookingId);
	    String username = userService.getUser().getUsername();
	    String userType = gymUserService.getType(); // Assuming this method returns the role/type of the user

	    if (booking != null && (booking.getUsername().equals(username) || userType.equalsIgnoreCase("ADMIN"))) {
	        SlotItemEmbed embed = new SlotItemEmbed(booking.getSlotId(), booking.getItemId());
	        SlotItem slotItem = slotItemDao.findItemById(embed);
	        
	        if (slotItem != null && slotItem.getSeatBooked() > 0) {
	            slotItem.setSeatBooked(slotItem.getSeatBooked() - 1);
	            slotItemDao.saveSlotItem(slotItem);
	        }
	        gymBookDao.deleteById(bookingId);
	    }
	    return new ModelAndView("redirect:/index");
	}



	
	@GetMapping("/slot-item-add/{id}")
	public ModelAndView saveItemSlots(@PathVariable Long id) {
		itemService.addNewItemsToSlots(id);
		return new ModelAndView("controlPanel");
	}
	@ExceptionHandler(SeatNotAvailableException.class)
	public ModelAndView handleNumberFormatException(SeatNotAvailableException exception) {
		String message="Seat Not Available For Booking";
		ModelAndView mv=new ModelAndView("errorPage");
		mv.addObject("errorMessage",message);
		return mv;
	}
    @ExceptionHandler(AlreadyBookedException.class)
    public ModelAndView handleAlreadyBookedException(AlreadyBookedException ex) {
        ModelAndView modelAndView = new ModelAndView("erroralreadyBooked");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
	@PostMapping("/slot-booking")
	public ModelAndView bookTheSlot(@ModelAttribute("slotItemRecord") SlotItem slotItem) {
		slotItemDaoImpl.saveSlotItem(slotItem);
		return new ModelAndView("controlPanel");
	}
	@GetMapping("/book-slot")
	public ModelAndView showSeatBookingPage() {
		return new ModelAndView("slotBooking");
	}
	
	@GetMapping("/get-name")
	public ResponseEntity<String> getFullName(Principal principal) {
		return ResponseEntity.ok(gymUserService.getName(principal));
	}
	
}