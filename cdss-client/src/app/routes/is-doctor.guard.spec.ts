import { TestBed, async, inject } from '@angular/core/testing';

import { IsDoctorGuard } from './is-doctor.guard';

describe('IsDoctorGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsDoctorGuard]
    });
  });

  it('should ...', inject([IsDoctorGuard], (guard: IsDoctorGuard) => {
    expect(guard).toBeTruthy();
  }));
});
