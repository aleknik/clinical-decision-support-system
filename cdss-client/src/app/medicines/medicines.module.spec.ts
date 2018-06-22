import { MedicinesModule } from './medicines.module';

describe('MedicinesModule', () => {
  let medicinesModule: MedicinesModule;

  beforeEach(() => {
    medicinesModule = new MedicinesModule();
  });

  it('should create an instance', () => {
    expect(medicinesModule).toBeTruthy();
  });
});
