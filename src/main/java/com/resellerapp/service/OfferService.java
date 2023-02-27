package com.resellerapp.service;


import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ConditionsInitService conditionsInitService;
    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, ConditionsInitService conditionsInitService,
                        UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.conditionsInitService = conditionsInitService;
        this.userRepository = userRepository;
    }

    public void add(AddOfferDTO addOfferDTO) {
        Condition condition = conditionsInitService.findCondition(addOfferDTO.getConditionName());
        Offer offer = new Offer();
        offer.setDescription(addOfferDTO.getDescription());
        offer.setPrice(addOfferDTO.getPrice());
        offer.setCondition(condition);
        offer.setSeller(userRepository.findById(addOfferDTO.getSellerId()).orElse(null));
        this.offerRepository.save(offer);
    }

    public Set<Offer> findOffersFromUser(Long userId) {
        return offerRepository.findBySellerId(userId);
    }

    public Set<Offer> findOffersNotFromUser(Long userId) {
        return offerRepository.findBySellerIdNot(userId)
                .stream()
                .filter(offer -> offer.getBuyer() == null)
                .filter(offer -> !Objects.equals(offer.getSeller().getId(), userId))
                .collect(Collectors.toSet());
    }

    public Set<Offer> findOffersUserBought(Long userId) {
        return offerRepository.findByBuyerId(userId)
                .stream()
                .filter(offer -> Objects.equals(offer.getBuyer().getId(), userId))
                .collect(Collectors.toSet());
    }

    public void delete(Long id) {
        offerRepository.deleteById(id);
    }

    @Transactional
    public void buy(Long id, Long buyerId) {
        offerRepository.updateBuyer(id, buyerId);
    }
}
