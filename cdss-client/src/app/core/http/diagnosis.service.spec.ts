import { TestBed, inject } from '@angular/core/testing';

import { DiagnosisService } from './diagnosis.service';

describe('DiagnosisService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiagnosisService]
    });
  });

  it('should be created', inject([DiagnosisService], (service: DiagnosisService) => {
    expect(service).toBeTruthy();
  }));
});
