import { TestBed, inject } from '@angular/core/testing';

import { TokenUtilsService } from './token-utils.service';

describe('TokenUtilsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TokenUtilsService]
    });
  });

  it('should be created', inject([TokenUtilsService], (service: TokenUtilsService) => {
    expect(service).toBeTruthy();
  }));
});
