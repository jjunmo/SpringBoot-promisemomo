package kr.co.promisemomo.module.member.service;

import kr.co.promisemomo.module.member.entity.Member;
import kr.co.promisemomo.module.member.entity.Promise;
import kr.co.promisemomo.module.member.entity.PromiseMember;
import kr.co.promisemomo.module.member.repository.PromiseMemberRepository;
import kr.co.promisemomo.module.member.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromiseService {

    private final PromiseMemberRepository promiseMemberRepository;
    private final PromiseRepository promiseRepository;

    public List<Promise> getPromises(Long id) {

        List<PromiseMember> promiseMembers = promiseMemberRepository.findByMember_Id(id);
        List<Long> promiseIds =
                promiseMembers
                        .stream()
                        .map(promiseMember -> promiseMember.getPromise().getId())
                        .distinct()
                        .collect(Collectors.toList());

        return promiseRepository.findByIdIn(promiseIds);


//        List<Long> promiseIds = new ArrayList<>();
//
//        for (PromiseMember promiseMember : promiseMembers) {
//
//            Long promiseMemberId = promiseMember.getId();
//
//            if (!promiseIds.contains(id)) {
//                promiseIds.add(id);
//            }
//        }
//
//        return promiseRepository.findByIdIn(promiseIds);
    }

}