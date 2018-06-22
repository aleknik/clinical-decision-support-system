import { DoctorsModule } from './doctors.module';

describe('DoctorsModule', () => {
  let doctorsModule: DoctorsModule;

  beforeEach(() => {
    doctorsModule = new DoctorsModule();
  });

  it('should create an instance', () => {
    expect(doctorsModule).toBeTruthy();
  });
});
