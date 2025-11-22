package com.tuan.movieservice.server;

import com.tuan.authservice.dto.UserVerify;
import com.tuan.authservice.exceptionhandler.builtin_exception.UsernameNotFoundException;
import com.tuan.authservice.model.User;
import com.tuan.authservice.repository.UserRepository;
import com.tuan.authservice.service.JwtService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {
    JwtService jwtService;
    UserRepository userRepository;
    public AuthGrpcService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
    @Override
    public void verify(AuthRequest authRequest, StreamObserver<AuthResponse> responseObserver) {
        UserVerify user = jwtService.verifyAccessToken(authRequest.getAccessToken());
        Optional<User> userOptional = userRepository.findDistinctByUsername(user.getUsername());
        if (userOptional.isEmpty()) throw new UsernameNotFoundException();
        AuthResponse response = AuthResponse.newBuilder()
                .setUserInfo(UserInfo.newBuilder()
                        .setUserRole(
                               Role.valueOf(user.getUserRole().name())
                        )
                        .setUserId(user.getUserId())
                        .build())
                .setMessage("authenticated")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
