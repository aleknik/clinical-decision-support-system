import { TestBed, async, inject } from '@angular/core/testing';

import { IsUnauthenticatedGuard } from './is-unauthenticated.guard';

describe('IsUnauthenticatedGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsUnauthenticatedGuard]
    });
  });

  it('should ...', inject([IsUnauthenticatedGuard], (guard: IsUnauthenticatedGuard) => {
    expect(guard).toBeTruthy();
  }));
});
