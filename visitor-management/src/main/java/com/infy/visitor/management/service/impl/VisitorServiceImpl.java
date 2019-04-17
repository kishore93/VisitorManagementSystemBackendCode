/**
 * 
 */
package com.infy.visitor.management.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.infy.visitor.management.domin.VisitorDetail;
import com.infy.visitor.management.entity.Visitor;
import com.infy.visitor.management.kafka.ProducerEmail;
import com.infy.visitor.management.repository.LocationRepository;
import com.infy.visitor.management.repository.VisitorRepository;
import com.infy.visitor.management.repository.VisitorTypeRepository;
import com.infy.visitor.management.service.QrService;
import com.infy.visitor.management.service.VisitorService;
import com.infy.visitor.management.utils.ImageUtils;

/**
 * @author neeraj.sanodiya
 *
 */
@Service
public final class VisitorServiceImpl implements VisitorService {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private ImageUtils imageUtils;

	@Autowired
	private VisitorTypeRepository visitorTypeRepository;

	@Autowired
	private ProducerEmail producerEmail;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private QrService qrService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.infy.visitor.management.service.VisitorService#visitors()
	 */
	@Override
	public List<VisitorDetail> findAll() {
		// TODO Auto-generated method stub
		return fromVisitor(visitorRepository.findAll());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infy.visitor.management.service.VisitorService#visitorsRefferdBy(java.
	 * lang.String)
	 */
	@Override
	public List<VisitorDetail> visitorsRefferdBy(long employeeCode) {
		// TODO Auto-generated method stub
		return fromVisitor(visitorRepository.refferBy(employeeCode));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.infy.visitor.management.service.VisitorService#addVisitor()
	 */
	@Override
	public List<VisitorDetail> addVisitor(List<VisitorDetail> visitors) {
		List<Visitor> entities = fromVisitorDetail(visitors);
		for (Visitor entity : entities) {

			try {
				entity = visitorRepository.save(entity);
				if (entity != null && entity.getId() > 0) {
					ObjectMapper mapper = new ObjectMapper();
					imageUtils.generateQrCode(mapper.writeValueAsString(entity), entity.getQrCodeUrl());
					List<VisitorDetail> visitorDetails = fromVisitor(entities);
					producerEmail.sendMessage(toJson(visitorDetails));

					return visitorDetails;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infy.visitor.management.service.VisitorService#verifyVisitor(java.lang.
	 * Long[])
	 */
	@Override
	public boolean verifyVisitor(Long id,boolean isPreApproved) {
		Visitor visitor=visitorRepository.findById(id).get();
		visitor.setPreApproved(isPreApproved);
		visitorRepository.save(visitor);
		return false;
	}

	public List<Visitor> fromVisitorDetail(List<VisitorDetail> listVisitors) {
		List<Visitor> list = null;
		if (!listVisitors.isEmpty()) {
			list = new ArrayList<Visitor>();
			for (VisitorDetail visitors : listVisitors) {
				Visitor visitor = new Visitor();
				if (visitors.getId() > 0)
					visitor.setId(visitors.getId());

				
				 if (visitors.isPreApproved())
					 visitor.setPreApproved(true);
				 
				if (visitors.getVisitorTypeId() > 0) {
					visitor.setVisitorType(visitorTypeRepository.findById(visitors.getVisitorTypeId()).get());
				}
				if (visitors.getLocationId() > 0) {
					visitor.setLocationId(locationRepository.findById(visitors.getLocationId()).get().getId());
				}
				if (visitors.getVisitorName().length() > 0)
					visitor.setVisitorName(visitors.getVisitorName());

				if (visitors.getReffererId() > 0)
					visitor.setReffererId(visitors.getReffererId());

				if (visitors.getVisitorMobile() > 0)
					visitor.setVisitorMobile(visitors.getVisitorMobile());

				if (visitors.getApproverEmail().length() > 0)
					visitor.setApproverEmail(visitors.getApproverEmail());

				visitor.setQrCodeUrl(System.currentTimeMillis() + "-" + visitor.getId() + "qr.png");
				list.add(visitor);
			}
		}
		return list;
	}

	public List<VisitorDetail> fromVisitor(List<Visitor> visitorslist) {
		List<VisitorDetail> list = null;
		if (!visitorslist.isEmpty()) {
			list = new ArrayList<VisitorDetail>();
			for (Visitor visitors : visitorslist) {
				VisitorDetail visitorDetail = new VisitorDetail();
				if (visitors.getId() > 0)
					visitorDetail.setId(visitors.getId());

				if (visitors.getVisitorName().length() > 0) {
					visitorDetail.setVisitorName(visitors.getVisitorName());
				}

				if (visitors.getApproverEmail().length() > 0) {
					visitorDetail.setApproverEmail(visitors.getApproverEmail());
				}

				if (visitors.getLocationId() > 0) {
					visitorDetail.setLocationName(
							locationRepository.findByLocationId(visitors.getLocationId()).getLocName());
				}
				if (visitors.getVisitorMobile() > 0) {
					visitorDetail.setVisitorMobile(visitors.getVisitorMobile());
				}
				if (visitors.getReffererId() > 0) {
					visitorDetail.setReffererId(visitors.getReffererId());
				}

				if (visitors.getQrCodeUrl().length() > 0) {
					visitorDetail.setQrCodeUrl(visitors.getQrCodeUrl());
					try {
						visitorDetail.setQrHtml(qrService.htmlQR(visitors.getQrCodeUrl()));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				list.add(visitorDetail);
			}
		}
		return list;
	}

	private String toJson(List<VisitorDetail> visitorDetails) {
		return new Gson().toJson(visitorDetails.get(0));

	}


}
